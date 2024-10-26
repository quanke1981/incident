import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => (
    <nav>
        <Link to='/'>Home</Link> | <Link to='/incidents'>All Incidents</Link> | <Link to='/new-incident'>New Incident</Link>
    </nav>
);

export default Navbar;