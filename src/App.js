import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Home from './pages/Home';
import Destinations from './pages/Destinations';
import Recommendations from './pages/Recommendations';
import Itinerary from './pages/Itinerary';
import Reviews from './pages/Reviews';
import About from './pages/About';
import './styles/global.css';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <main>
        <Routes>
          <Route path="/"                element={<Home />} />
          <Route path="/destinations"    element={<Destinations />} />
          <Route path="/recommendations" element={<Recommendations />} />
          <Route path="/itinerary"       element={<Itinerary />} />
          <Route path="/reviews"         element={<Reviews />} />
          <Route path="/about"           element={<About />} />
        </Routes>
      </main>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
/*import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;*/
