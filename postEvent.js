import React, {useState} from 'react'
import { Button, Form } from 'semantic-ui-react'
import axios from 'axios';
import { Link, useHistory } from 'react-router-dom';

export default function Create() {
    const [topic, setTopic] = useState('');
    const [description, setDescription] = useState('');
    const [author, setAuthor] = useState('');
    const [date, setDate] = useState('');
    const [durationInMins, setDurationInMins] = useState('');


    const postData = () => {
        axios.post('http://localhost:8080/event', {
            topic,
            description,
            author,
            date,
            durationInMins
        }, { headers: {
            Authorization: localStorage.getItem("JWT")
        }
        })
    }

    return (
        <><h4>Create event</h4>
        <div  className='create-form'>
            <Form>
                <Form.Field>
                    <label>Topic</label>
                    <input placeholder='Topic' onChange={(e) => setTopic(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Description</label>
                    <input placeholder='Description' onChange={(e) => setDescription(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Author</label>
                    <input placeholder='Author' onChange={(e) => setAuthor(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Date</label>
                    <input placeholder='YYYY-MM-DDThh:mm:ss' type={'datetime-local'} onChange={(e) => setDate(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>DurationInMins</label>
                    <input placeholder='Duration in minutes' type={'number'} onChange={(e) => setDurationInMins(e.target.value)}/>
                </Form.Field>
                <Link to={'/'}><Button onClick={postData} type='submit'>Submit</Button></Link>
            </Form>
        </div></>
    )
}