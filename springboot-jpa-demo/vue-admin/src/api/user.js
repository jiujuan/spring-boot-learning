import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
})

export const userApi = {
  getUsers(page = 0, size = 10) {
    return api.get(`/users?page=${page}&size=${size}`)
  },
  createUser(user) {
    return api.post('/users', user)
  },
  updateUser(id, user) {
    return api.put(`/users/${id}`, user)
  },
  deleteUser(id) {
    return api.delete(`/users/${id}`)
  },
  getUserByUsername(username) {
    return api.get(`/users/username/${username}`)
  }
}