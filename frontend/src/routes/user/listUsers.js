import * as React from "react";
import { List, Datagrid, TextField, EmailField } from 'react-admin';

export const UserList = props => (
    <List {...props} title="Список пользователей">
        <Datagrid rowClick="edit">
            <TextField source="id" />
            <TextField source="name" title="Имя" />
            <TextField source="username" title="Логин" />
            <EmailField source="email" />
            <TextField source="phone" />
        </Datagrid>
    </List>
);