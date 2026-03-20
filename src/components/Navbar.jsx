import { NavLink } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-logo">✈ Wandr</div>
      <ul className="navbar-links">
        {[
          { to: '/',               label: 'Home' },
          { to: '/destinations',   label: 'Destinations' },
          { to: '/recommendations',label: 'Recommendations' },
          { to: '/itinerary',      label: 'Itinerary' },
          { to: '/reviews',        label: 'Reviews' },
          { to: '/about',          label: 'About' },
        ].map(({ to, label }) => (
          <li key={to}>
            <NavLink to={to} className={({ isActive }) => isActive ? 'active' : ''}>
              {label}
            </NavLink>
          </li>
        ))}
      </ul>
    </nav>
  );
}

export default Navbar;