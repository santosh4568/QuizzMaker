import axios from 'axios';
const USER_API_BASE_URL = 'http://localhost:8080/user';

export async function login(user) {
    return axios.post(USER_API_BASE_URL + '/login', user);
}

export async function signup(user) {
    return axios.post(USER_API_BASE_URL + '/register', user);
}

export async function logout() {
    return axios.post(USER_API_BASE_URL + '/logout');
}

export async function getUsers() {
    return axios.get(USER_API_BASE_URL);
}

export async function updateUser(user) {
    return axios.put(USER_API_BASE_URL + '/update', user);
}

export async function deleteUser(userId) {
    return axios.delete(USER_API_BASE_URL + '/delete' + userId);
}