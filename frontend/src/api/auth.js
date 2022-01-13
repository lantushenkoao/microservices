import {anonymousQuery} from './baseApi';

export const login = (login, password) => {
    return anonymousQuery.post('/api/auth/login',
        {
            'login': login,
            'password': password
        }
    );
}

