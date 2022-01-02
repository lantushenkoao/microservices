import * as React from "react";
import {
    Create,
    Edit,
    SimpleForm,
    TextInput,
} from 'react-admin';


export const CreateUser = props => (
    <Create {...props}>
        <SimpleForm>
            <TextInput source="name" />
            <TextInput source="login" />
            <TextInput source="email" />
            <TextInput source="password"/>
        </SimpleForm>
    </Create>
);