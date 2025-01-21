// App.jsx
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ProtectedRoute from './components/ProtectedRoute';
import Home from './components/Home';
import Login from './components/Login';
import Logout from './components/Logout';
import './App.css';
import Signup from './components/Signup';
function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                {/* <Route path="/signup" element={<Signup />} /> */}
                <Route 
                    path="/createQuizz" 
                    element={
                        <ProtectedRoute requiredRole="CONDUCTOR">
                            {/* <CreateQuizz /> */}
                        </ProtectedRoute>
                    } 
                />
                <Route 
                    path="/viewQuizz" 
                    element={
                        <ProtectedRoute requiredRole="CONDUCTOR">
                            {/* <ViewQuizz /> */}
                        </ProtectedRoute>
                    } 
                />
                <Route 
                    path="/selectTitle" 
                    element={
                        <ProtectedRoute requiredRole="USER">
                            {/* <SelectTitle /> */}
                        </ProtectedRoute>
                    } 
                />
                <Route 
                    path="/profile" 
                    element={
                        <ProtectedRoute>
                            {/* <Profile /> */}
                        </ProtectedRoute>
                    } 
                />
                <Route path="/logout" element={<Logout />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;