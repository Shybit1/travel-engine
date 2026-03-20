import { useState, useEffect } from 'react';
import { getDestinations } from '../services/api';
import DestinationCard from '../components/DestinationCard';
import './Destinations.css';

const CONTINENTS = ['All', 'Asia', 'Europe', 'Americas', 'Africa', 'Oceania'];
const BUDGETS    = ['All', 'Budget', 'Mid-Range', 'Luxury'];
const CLIMATES   = ['All', 'Tropical', 'Arid', 'Temperate', 'Cold'];
const TRIP_TYPES = ['All', 'Relaxation', 'Adventure', 'Culture', 'City'];

function Destinations() {
  const [destinations, setDestinations] = useState([]);
  const [filtered, setFiltered]         = useState([]);
  const [loading, setLoading]           = useState(true);
  const [error, setError]               = useState(null);
  const [search, setSearch]             = useState('');
  const [filters, setFilters]           = useState({
    continent: 'All', budget: 'All', climate: 'All', tripType: 'All'
  });
  const [selected, setSelected]         = useState(null);

  useEffect(() => {
    getDestinations()
      .then(data => { setDestinations(data); setFiltered(data); setLoading(false); })
      .catch(() => { setError('Could not load destinations.'); setLoading(false); });
  }, []);

  useEffect(() => {
    let result = destinations;
    if (search) result = result.filter(d =>
      d.name.toLowerCase().includes(search.toLowerCase()) ||
      d.country.toLowerCase().includes(search.toLowerCase())
    );
    if (filters.continent !== 'All') result = result.filter(d => d.continent === filters.continent);
    if (filters.budget    !== 'All') result = result.filter(d => d.budgetLevel === filters.budget);
    if (filters.climate   !== 'All') result = result.filter(d => d.climate === filters.climate);
    if (filters.tripType  !== 'All') result = result.filter(d => d.tripType === filters.tripType);
    setFiltered(result);
  }, [search, filters, destinations]);

  const setFilter = (key, val) => setFilters(f => ({ ...f, [key]: val }));

  if (loading) return <div className="page-loading">Loading destinations...</div>;
  if (error)   return <div className="page-error">{error}</div>;

  return (
    <div className="destinations-page">
      <div className="destinations-header">
        <h1>Explore Destinations</h1>
        <span className="dest-count">{filtered.length} of {destinations.length} destinations</span>
      </div>

      <div className="filter-bar">
        <input
          className="search-input"
          placeholder="Search destinations or countries..."
          value={search}
          onChange={e => setSearch(e.target.value)}
        />
        <FilterGroup label="Continent" options={CONTINENTS} active={filters.continent} onChange={v => setFilter('continent', v)} />
        <FilterGroup label="Budget"    options={BUDGETS}    active={filters.budget}    onChange={v => setFilter('budget', v)} />
        <FilterGroup label="Climate"   options={CLIMATES}   active={filters.climate}   onChange={v => setFilter('climate', v)} />
        <FilterGroup label="Trip Type" options={TRIP_TYPES} active={filters.tripType}  onChange={v => setFilter('tripType', v)} />
      </div>

      {filtered.length === 0 ? (
        <div className="empty-state">
          <span>🧭</span>
          <p>No destinations match your filters.</p>
          <button onClick={() => { setSearch(''); setFilters({ continent:'All', budget:'All', climate:'All', tripType:'All' }); }}>
            Clear Filters
          </button>
        </div>
      ) : (
        <div className="destinations-grid">
          {filtered.map(d => (
            <DestinationCard key={d.id} destination={d} onClick={() => setSelected(d)} />
          ))}
        </div>
      )}

      {selected && <Modal destination={selected} onClose={() => setSelected(null)} />}
    </div>
  );
}

function FilterGroup({ label, options, active, onChange }) {
  return (
    <div className="filter-group">
      <span className="filter-label">{label}</span>
      <div className="filter-pills">
        {options.map(o => (
          <button key={o} className={`pill ${active === o ? 'active' : ''}`} onClick={() => onChange(o)}>
            {o}
          </button>
        ))}
      </div>
    </div>
  );
}

function Modal({ destination: d, onClose }) {
  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-card" onClick={e => e.stopPropagation()}>
        <button className="modal-close" onClick={onClose}>✕</button>
        <img src={d.imageUrl} alt={d.name} className="modal-img" />
        <div className="modal-body">
          <h2>{d.name}</h2>
          <p className="modal-country">{d.country}</p>
          <div className="modal-tags">
            <span className="tag">{d.tripType}</span>
            <span className="tag">{d.budgetLevel}</span>
            <span className="tag">{d.climate}</span>
          </div>
          <p className="modal-desc">{d.description}</p>
          <div className="modal-meta">
            {d.language  && <span>🗣 {d.language}</span>}
            {d.bestTime  && <span>📅 Best time: {d.bestTime}</span>}
            <span>⏱ {d.minDuration}–{d.maxDuration} days</span>
          </div>
          {d.avgRating && (
            <div className="modal-rating">
              ⭐ {Number(d.avgRating).toFixed(1)} ({d.reviewCount} reviews)
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Destinations;