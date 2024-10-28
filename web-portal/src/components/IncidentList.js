import React, {useEffect, useState} from 'react';
import {getIncidents, deleteIncident} from '../api';
import { useNavigate } from 'react-router-dom';

const IncidentList = () => {
    const [incidents, setIncidents] = useState([]);
    const [page, setPage] = useState(0);
    const [size] = useState(20);
    const [totalPages, setTotalPages] = useState(0);
    const [searchQuery, setSearchQuery] = useState('');
    const [titleFilter, setTitleFilter] = useState('');
    const navigate = useNavigate();
    const params = {
      page,
      size,
      title: searchQuery,
    };

    useEffect(() => {
        loadIncidents(params);
    }, [page, searchQuery]);

    const loadIncidents = (params) => {
        getIncidents(params)
        .then(response => {  
            setIncidents(response.data.content);
            setPage(response.data.pageNumber);
            setTotalPages(response.data.totalPages);

        })
        .catch(error => console.error('Error fetching incidents: ', error));
    };

    const editIncident = (id) => {
        navigate(`/edit-incident/${id}`);
    }

    const delIncident = (id) => {
        deleteIncident(id).then(() => loadIncidents({page: page, pageSize: size, title: searchQuery}));        
    }


    const filteredIncidents = incidents.filter((incident) => incident.title.toLowerCase().includes(searchQuery.toLowerCase()));

    return (
        <div>
            <h2>Incident List</h2>
            <input type="text" placeholder="Search incidents by title..." value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)}/> 
            <ul>
                <li className="header">
                    <div>Title</div>
                    <div>Description</div>
                    <div>Status</div>
                    <div className="action">&nbsp;</div>
                </li>
                {filteredIncidents.length > 0  && filteredIncidents.map((incident) => (
                    <li key={incident.id}>
                        <div>{incident.title}</div>
                        <div>{incident.description}</div>
                        <div>{incident.status}</div>
                        <div className="action">
                            <button onClick={() => editIncident(incident.id)}>Edit</button>
                            <button onClick={() => delIncident(incident.id)}>Delete</button>
                        </div>
                    </li>
                ))}
            </ul>
            <div>
                <button disabled={page === 0} onClick={() => setPage(page - 1)}>Previous</button>
                <span> Page {page + 1} of {totalPages} </span>
                <button disabled={page + 1 === totalPages} onClick={() => setPage(page + 1)}>Next</button>
            </div>
        </div>
    );
};

export default IncidentList;