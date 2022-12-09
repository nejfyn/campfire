import React, {useState} from 'react'
import { Button, Form } from 'semantic-ui-react'
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [token, setToken] = useState('');

    const postCreds = () => {
        axios.post('http://localhost:8080/login', {
            username,
            password
        }).then((response) => {
            console.log(response.data);
            localStorage.setItem("JWT", response.data.Authorization);
        })
    }

    return (
        <><h4>Log in</h4>
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
                <Link to={'/'}><Button onClick={postCreds} type='submit' className='button'>Login</Button></Link>
                <Link to={'/sign_up'}><Button className='button'>Don't have an account?</Button></Link>
            </Form>
        </div></>
    )
}