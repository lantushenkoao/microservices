import { fetchUtils } from 'react-admin';
import {LOCAL_STORAGE_AUTH} from './constants';

export default (url, options = {}) => {

    if (!options.headers) {
        options.headers = new Headers({ Accept: 'application/json' });
    }
    options.headers.set('authorization', 'Bearer ' + localStorage.getItem(LOCAL_STORAGE_AUTH));


    return fetchUtils.fetchJson(url, options);
};
