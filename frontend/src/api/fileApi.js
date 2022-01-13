import {authenticatedQuery} from './baseApi';
import {LOCAL_STORAGE_AUTH} from "./constants";

export const requestFile = () => {
    return authenticatedQuery.post('/api/file/query',
        {
            'fileName': 'test'
        }
    );
}

