import api from './baseApi';

export const login = (login, password) => {
    return api.post('/api/auth/login',
        {
            'login': login,
            'password': password
        }
    );
}

