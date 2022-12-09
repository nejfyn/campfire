import React, {useEffect, useState} from 'react'
import { Button, Form } from 'semantic-ui-react'
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Update() {
    const [topic, setTopic] = useState('');
    const [description, setDescription] = useState('');
    const [author, setAuthor] = useState('');
    const [date, setDate] = useState('');
    const [durationInMins, setDurationInMins] = useState('');
    const [id, setId] = useState(0);


    useEffect(() => {
        setId(localStorage.getItem('ID'));
        setTopic(localStorage.getItem('Topic'));
        setDescription(localStorage.getItem('Description'));
        setAuthor(localStorage.getItem('Author'));
        setDate(localStorage.getItem('Date'));
        setDurationInMins(localStorage.getItem('Duration in minutes'));
    }, []);

    const eraseData = () => {
        localStorage.removeItem('ID');
        localStorage.removeItem('Topic');
        localStorage.removeItem('Description');
        localStorage.removeItem('Author');
        localStorage.removeItem('Date');
        localStorage.removeItem('Duration in minutes');
    }

    const updateAPIData = () => {
        axios.put('http://localhost:8080/event/'+id, {
            topic, description, author, date, durationInMins
        }, { headers: {
            Authorization: localStorage.getItem("JWT")
        }
        })
        .then(() => {
            eraseData();
        })
    }

    
    return (
        <div  className='create-form'>
            <Form>
                <Form.Field>
                    <label>Topic</label>
                    <input placeholder='Topic' value={topic} onChange={(e) => setTopic(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Description</label>
                    <input placeholder='Description' value={description} onChange={(e) => setDescription(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Author</label>
                    <input placeholder='Author' value={author} onChange={(e) => setAuthor(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Date</label>
                    <input placeholder='YYYY-MM-DDThh:mm:ss' type={'datetime-local'} value={date} onChange={(e) => setDate(e.target.value)}/>
                </Form.Field>
                <Form.Field>
                    <label>Duration in minutes</label>
                    <input placeholder='Duration in minutes' type={'number'} value={durationInMins} onChange={(e) => setDurationInMins(e.target.value)}/>
                </Form.Field>
                <Link to={'/'}><Button type='submit' onClick={updateAPIData} className='button'>Update</Button></Link>
            </Form>
        </div>
    )
}