import './App.css';
import CreateUser from './components/createUser';
import CreateEvent from './components/postEvent';
import Read from './components/readEvents';
import UpdateEvent from './components/updateEvent';
import Login from './components/login';
import { BrowserRouter as Router, Link, Route, Routes, useHistory } from 'react-router-dom'

function App() {
  return (
    <div className="main">
      <h2 className="main-header">Campfires</h2>
    <Router>
    <Routes>
            <Route path='sign_up' element={<CreateUser/>} />
            <Route path='create_event' element={<CreateEvent/>} />
            <Route path='' element={<Read/>} />
            <Route path='update' element={<UpdateEvent/>} />
            <Route path='login' element={<Login/>} />
    </Routes>
    </Router>
    </div>
  );
}

export default App;