import axios from 'axios';
import {BACKEND_HOST} from './constants';

export default axios.create({
    baseURL: BACKEND_HOST
});
