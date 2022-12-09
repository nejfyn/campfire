import React, {useEffect, useState} from 'react';
import { Button, Table } from 'semantic-ui-react'
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Read() {
    const [APIData, setAPIData] = useState([]);
    
    useEffect(() => {
        axios.get('http://localhost:8080/event')
        .then((response) => {
            setAPIData(response.data);
        })
    }, [])

    // useEffect(() => {
    //     refresh();
    // })

    const refresh = () => {
        window.location.reload();
    }

    const setData = (data) => {
        let {id, topic, description, author, date, durationInMins} = data;
        localStorage.setItem('ID', id);
        localStorage.setItem('Topic', topic);
        localStorage.setItem('Description', description);
        localStorage.setItem('Author', author);
        localStorage.setItem('Date', date);
        localStorage.setItem('Duration in minutes', durationInMins);
    }

    const eraseData = () => {
        localStorage.removeItem('ID');
        localStorage.removeItem('Topic');
        localStorage.removeItem('Description');
        localStorage.removeItem('Author');
        localStorage.removeItem('Date');
        localStorage.removeItem('Duration in minutes');
    }

    const getData = () => {
        axios.get('http://localhost:8080/event')
        .then((getData) => {
            setAPIData(getData.data);
        })
    }

    const onDelete = (id) => {
        axios.delete('http://localhost:8080/event/'+id,{ 
            headers: {
            Authorization: localStorage.getItem("JWT")
        }
        })
        .then(() => {
            getData();
        })
        .then(() => {
            eraseData();
        })
    }

    const logout = () => {
        localStorage.removeItem("JWT")
    }

    if (localStorage.getItem("JWT") == null) {
        return (
            <>
            <div className='table'>
                <div>
                    <Link to={'/login'}><Button>Log in</Button></Link>
                    <Link to={'/sign_up'}><Button>Sign up</Button></Link>
                    <Button onClick={refresh}>Reload page</Button>
                </div>
            <Table singleLine>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Topic</Table.HeaderCell>
                        <Table.HeaderCell>Description</Table.HeaderCell>
                        <Table.HeaderCell>Author</Table.HeaderCell>
                        <Table.HeaderCell>Date</Table.HeaderCell>
                        <Table.HeaderCell>Duration in minutes</Table.HeaderCell>
                        <Table.HeaderCell>Update</Table.HeaderCell>
                        <Table.HeaderCell>Delete</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>
                    {APIData.map((data) => {
                        return (
                            <Table.Row>
                                <Table.Cell>{data.topic}</Table.Cell>
                                <Table.Cell>{data.description}</Table.Cell>
                                <Table.Cell>{data.author}</Table.Cell>
                                <Table.Cell>{data.date}</Table.Cell>
                                <Table.Cell>{data.durationInMins}</Table.Cell>
                                <Link to='/update'><Table.Cell><Button onClick={() => setData(data)}>Update</Button></Table.Cell></Link>
                                <Table.Cell><Button onClick={() => {if(window.confirm('Delete the event?')){onDelete((data.id))};}}>Delete</Button></Table.Cell>
                            </Table.Row>
                        );
                    })}
                    <Table.Row>
                        <Link to='create_event'><Table.Cell><Button className='addButton'>Create event</Button></Table.Cell></Link>
                    </Table.Row>
                </Table.Body>
            </Table>
        </div></>
        )
    }
    else {
        return (
            <div className='table'>
                <div>
                    <Button onClick={logout}>Log out</Button>
                    <Button onClick={refresh}>Reload page</Button>
                </div>
                    <Table singleLine>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell>Topic</Table.HeaderCell>
                                <Table.HeaderCell>Description</Table.HeaderCell>
                                <Table.HeaderCell>Author</Table.HeaderCell>
                                <Table.HeaderCell>Date</Table.HeaderCell>
                                <Table.HeaderCell>Duration in minutes</Table.HeaderCell>
                                <Table.HeaderCell>Update</Table.HeaderCell>
                                <Table.HeaderCell>Delete</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
    
                        <Table.Body>
                            {APIData.map((data) => {
                                return (
                                    <Table.Row>
                                        <Table.Cell>{data.topic}</Table.Cell>
                                        <Table.Cell>{data.description}</Table.Cell>
                                        <Table.Cell>{data.author}</Table.Cell>
                                        <Table.Cell>{data.date}</Table.Cell>
                                        <Table.Cell>{data.durationInMins}</Table.Cell>
                                        <Link to='/update'><Table.Cell><Button onClick={() => setData(data)}>Update</Button></Table.Cell></Link>
                                        <Table.Cell><Button onClick={() => {if(window.confirm('Delete the event?')){onDelete((data.id))};}}>Delete</Button></Table.Cell>
                                    </Table.Row>
                                );
                            })}
                            <Table.Row>
                                <Link to='create_event'><Table.Cell><Button className='addButton'>Create event</Button></Table.Cell></Link>
                            </Table.Row>
                        </Table.Body>
                    </Table>
                </div>
            
        )
    }
}