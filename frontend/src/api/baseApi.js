import axios from 'axios';
import {BACKEND_HOST, LOCAL_STORAGE_AUTH} from './constants';

export const anonymousQuery = axios.create({
    baseURL: BACKEND_HOST
});

export const authenticatedQuery = axios.create({
    baseURL: BACKEND_HOST,
    headers: {
        'Authorization': `Bearer ${localStorage.getItem(LOCAL_STORAGE_AUTH)}`
    }
});