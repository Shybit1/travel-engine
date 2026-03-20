import './DestinationCard.css';

function DestinationCard({ destination: d, onClick }) {
  return (
    <div className="dest-card" onClick={onClick}>
      <div className="dest-card-img-wrap">
        <img src={d.imageUrl} alt={d.name} className="dest-card-img" />
        <span className="dest-card-badge trip">{d.tripType}</span>
        <span className="dest-card-badge budget">{d.budgetLevel}</span>
      </div>
      <div className="dest-card-body">
        <h3>{d.name}</h3>
        <p className="dest-card-country">{d.country}</p>
        <p className="dest-card-desc">{d.description?.slice(0, 90)}...</p>
        {d.avgRating && (
          <div className="dest-card-rating">
            {'★'.repeat(Math.round(d.avgRating))}{'☆'.repeat(5 - Math.round(d.avgRating))}
            <span> {Number(d.avgRating).toFixed(1)}</span>
          </div>
        )}
      </div>
    </div>
  );
}

export default DestinationCard;