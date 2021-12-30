import {LOCAL_STORAGE_AUTH} from '../api/constants';
import {login} from '../api/auth';

function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};


export default {
    login: ({ username, password }) =>  {
        return login(username, password).then(response=>{
            localStorage.setItem(LOCAL_STORAGE_AUTH, response.headers['authorization']);
        });
    },

    logout: () => {
        localStorage.removeItem(LOCAL_STORAGE_AUTH);
        return Promise.resolve();
    },

    checkError: ({ status }) => {
        if (status === 401 || status === 403) {
            localStorage.removeItem(LOCAL_STORAGE_AUTH);
            return Promise.reject();
        }
        return Promise.resolve();
    },
    checkAuth: () => {
        return localStorage.getItem(LOCAL_STORAGE_AUTH)
            ? Promise.resolve()
            : Promise.reject();
    },
    // called when the user navigates to a new location, to check for permissions / roles
    getPermissions: () => Promise.resolve(),
};
