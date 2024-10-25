import React, { useEffect, useState } from 'react';
import { FaPlus, FaEdit, FaTrash, FaEye } from 'react-icons/fa';
// import '../index.css';

const data = [
    { id: 1, name: 'Incident 1', description: 'Description 1' },
    { id: 2, name: 'Incident 2', description: 'Description 2' },
    { id: 3, name: 'Incident 3', description: 'Description 3' },
];

const IncidentsManagement = () => {
    const [incidents, setIncidents] = useState([]);

    const handleAdd = () => {
        // 添加事件的逻辑
    };

    const handleEdit = (id) => {
        // 编辑事件的逻辑
    };

    const handleDelete = (id) => {
        // 删除事件的逻辑
    };

    const handleView = (id) => {
        // 查看事件的逻辑
    };

    useEffect(() => {
        // Call list api
        setIncidents(data.concat());
    }, []);

    return (
        <div>
            <h1>Incidents Management</h1>
            <button onClick={handleAdd}>
                <FaPlus /> Add Incident
            </button>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {incidents.map((incident) => (
                        <tr key={incident.id}>
                            <td>{incident.id}</td>
                            <td>{incident.name}</td>
                            <td>{incident.description}</td>
                            <td>
                                <button onClick={() => handleView(incident.id)}>
                                    <FaEye />
                                </button>
                                <button onClick={() => handleEdit(incident.id)}>
                                    <FaEdit />
                                </button>
                                <button onClick={() => handleDelete(incident.id)}>
                                    <FaTrash />
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default IncidentsManagement;
