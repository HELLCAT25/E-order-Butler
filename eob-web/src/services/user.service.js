//import axios from 'axios';
import axios, * as others from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://54.84.55.238/eOrderButler/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }

  getAllShoppingOrders() {
    return axios.get(API_URL + 'getAllShoppingOrders', { headers: authHeader() });
  }



}

export default new UserService();
