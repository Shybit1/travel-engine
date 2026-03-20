import { NavLink } from 'react-router-dom';
import './Footer.css';

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-inner">
        <div className="footer-brand">
          <span className="footer-logo">✈ Wandr</span>
          <p>Discover your next adventure</p>
        </div>
        <ul className="footer-links">
          <li><NavLink to="/">Home</NavLink></li>
          <li><NavLink to="/destinations">Destinations</NavLink></li>
          <li><NavLink to="/recommendations">Recommendations</NavLink></li>
          <li><NavLink to="/itinerary">Itinerary</NavLink></li>
          <li><NavLink to="/reviews">Reviews</NavLink></li>
          <li><NavLink to="/about">About</NavLink></li>
        </ul>
        <div className="footer-external">
          <p>Travel inspiration from{' '}
            <a href="https://www.lonelyplanet.com" target="_blank" rel="noreferrer">
              Lonely Planet
            </a>
          </p>
        </div>
      </div>
      <div className="footer-bottom">
        Built for Yukthi 2026 — CSE Technical Events
      </div>
    </footer>
  );
}

export default Footer;