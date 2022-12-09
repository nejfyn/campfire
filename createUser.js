import React, {useState} from 'react'
import { Button, Form } from 'semantic-ui-react'
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Create() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [authority, setAuthority] = useState('USER');
    const postData = () => {
        axios.post('http://localhost:8080/user', {
            username,
            password,
            authority
        })
    }

    return (
        <><h4>Sign up</h4>
        <div  className='create-form'>
            <Form>
                <Form.Field>
                    <label>Username</label>
                    <input placeholder='Username' onChange={(e) => setUsername(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Password</label>
                    <input placeholder='Password' type='password' onChange={(e) => setPassword(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Authority</label>
                    <select onChange={(e) => setAuthority(e.target.value)}>
                        <option value="USER">User</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </Form.Field>
                <Link to={'/login'}><Button onClick={postData} type='submit' className='button'>Sign up</Button></Link>
                <Link to={'/login'}><Button className='button'>Already have an account?</Button></Link>
            </Form>
        </div></>
    )
}