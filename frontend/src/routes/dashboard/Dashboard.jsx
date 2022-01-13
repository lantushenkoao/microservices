import * as React from "react";
import {requestFile} from "../../api/fileApi";
import './Dashboard.scss';

export const Dashboard = props => (
    <div>
        Dashboard. Главная страница
        <div className="dashboard-button" onClick={requestFile}>Запросить файл</div>
    </div>
);