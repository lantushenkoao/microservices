import * as React from "react";
import {requestFile} from "../../api/fileApi";
import './Dashboard.scss';
import Button from '@material-ui/core/Button';

export const Dashboard = props => (
    <div>
        <h1>Dashboard. Главная страница</h1>
        <Button variant="outlined" onClick={requestFile}>Запросить файл</Button>
    </div>
);