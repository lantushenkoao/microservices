import * as React from "react";
import {
    EditButton,
    Edit,
    SimpleForm,
    TextInput,
} from 'react-admin';


export const EditUser = props => (
    <Edit {...props}>
        <SimpleForm>
            <TextInput source="id" />
            <TextInput source="name" />
            <TextInput source="login" />
            <TextInput source="email" />
        </SimpleForm>
    </Edit>
);