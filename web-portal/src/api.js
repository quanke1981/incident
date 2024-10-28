import axios from 'axios';

const API_URL = `${process.env.REACT_APP_API_URL}/incidents`;

export const getIncidents = (params) =>  axios.get(API_URL, { params },  { 'Access-Control-Allow-Origin': '*' });
export const createIncident = (data) => axios.post(API_URL, [data], { 'Access-Control-Allow-Origin': '*' });
export const updateIncident = (id, data) => axios.put(`${API_URL}/${id}`, data, { 'Access-Control-Allow-Origin': '*' });
export const deleteIncident = (id) => axios.delete(`${API_URL}/${id}`, { 'Access-Control-Allow-Origin': '*' });