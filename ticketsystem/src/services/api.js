import axios from "axios";

const BASE_URL = 'http://localhost:8080/api/tickets';

export const getAvailableTickets = async () => {
    try{
        const response = await axios.get(`${BASE_URL}/available`);
        return response.data;
    }catch (error) {
        console.error('Error fetching available tickets: ', error);
        throw error;
    }
};

export const submitConfiguration = async (configData) =>{
    try{
        const response = await axios.post(`${BASE_URL}/config`, configData);
        return response.data;
    }catch (error) {
        console.error('Error submitting configuration: ', error);
        throw error;
    }
};

export const startSystem = async () =>{
    try{
        const response = await axios.post(`${BASE_URL}/start`);
        return response.data;
    }catch (error) {
        console.error('Error starting system: ', error);
        throw error;
    }
};

export const stopSystem = async ()=>{
    try{
        const response = await axios.post(`${BASE_URL}/stop`);
        return response.data;
    }catch (error) {
        console.error('Error terminating system: ', error);
        throw error;
    }
};