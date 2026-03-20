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