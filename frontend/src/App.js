import './App.css';
import { Admin, Resource, EditGuesser } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';
import {UserList} from './routes/user/listUsers';
import {EditUser} from './routes/user/editUser';
import {CreateUser} from "./routes/user/createUser";
import authProvider from './auth/authProvider';
import httpClient from "./api/httpClient";
import {BACKEND_HOST} from './api/constants';
import polyglotI18nProvider from 'ra-i18n-polyglot';
import russianMessages from 'ra-language-russian';
import {Dashboard} from "./routes/dashboard/Dashboard";
import { customLightTheme } from './themes/customLightTheme';

const i18nProvider = polyglotI18nProvider(() => russianMessages, 'ru');


const dataProvider = jsonServerProvider(BACKEND_HOST, httpClient);
const App = () => (
    <Admin dataProvider={dataProvider}
           authProvider={authProvider}
           i18nProvider={i18nProvider}
           theme={customLightTheme}>
        <Resource name="blankPage" options={{ label: 'Домашняя страница' }} list={Dashboard}/>
        <Resource name="api/users" options={{ label: 'Пользователи' }} edit={EditUser}  list={UserList} create={CreateUser} />
    </Admin>
);


export default App;
