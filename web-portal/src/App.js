import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import NavBar from './components/NavBar';
import HomePage from './pages/HomePage';
import IncidentPage from './pages/IncidentPage';
import NewIncidentPage from './pages/NewIncidentPage';
import EditIncidentPage from './pages/EditIncidentPage';

function App() {
    return (
        <Router>
            <div>
                <NavBar />
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/incidents" element={<IncidentPage />} />                    
                    <Route path="/new-incident" element={<NewIncidentPage />} />
                    <Route path="/edit-incident/:id" element={<EditIncidentPage />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;