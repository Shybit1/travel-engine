import axios from 'axios';

const api = axios.create({ baseURL: 'http://localhost:8080/api' });

export const getDestinations = (filters = {}) =>
  api.get('/destinations', { params: filters }).then(r => r.data);

export const getDestinationById = (id) =>
  api.get(`/destinations/${id}`).then(r => r.data);

export const getReviews = (destination_id = null) =>
  api.get('/reviews', { params: destination_id ? { destination_id } : {} }).then(r => r.data);

export const postReview = (data) =>
  api.post('/reviews', data).then(r => r.data);

export const markHelpful = (id) =>
  api.patch(`/reviews/${id}/helpful`).then(r => r.data);

export const getItineraries = (user_name = null) =>
  api.get('/itineraries', { params: user_name ? { user_name } : {} }).then(r => r.data);

export const getItineraryById = (id) =>
  api.get(`/itineraries/${id}`).then(r => r.data);

export const postItinerary = (data) =>
  api.post('/itineraries', data).then(r => r.data);

export const deleteItinerary = (id) =>
  api.delete(`/itineraries/${id}`).then(r => r.data);

export const getRecommendations = (answers) =>
  api.post('/recommendations', answers).then(r => r.data);

export const getStats = () =>
  api.get('/stats').then(r => r.data);