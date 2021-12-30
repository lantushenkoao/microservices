import './App.css';
import { Admin, Resource, EditGuesser } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';
import {UserList} from './routes/users';
import {EditUser} from './routes/editUser';
import authProvider from './auth/authProvider';
import httpClient from "./api/httpClient";
import {BACKEND_HOST} from './api/constants';


const dataProvider = jsonServerProvider(BACKEND_HOST, httpClient);
const App = () => (
    <Admin dataProvider={dataProvider} authProvider={authProvider}>
        <Resource name="api/users" edit={EditUser}  list={UserList} />
    </Admin>
);


export default App;
