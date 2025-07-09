import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/budgets';

const budgetApi = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 10000, 
});

export const testConnection = async () => {
  try {
    const response = await budgetApi.get('/health');
    console.log('Backend connection test:', response.data);
    return true;
  } catch (error) {
    console.error('Backend connection failed:', error.message);
    return false;
  }
};

export const getBudgets = async (month = null) => {
  try {
    const params = month ? { month } : {};
    console.log('Fetching budgets with params:', params);
    const response = await budgetApi.get('', { params });
    console.log('Budgets response:', response.data);
    return response.data;
  } catch (error) {
    console.error('Error fetching budgets:', error);
    if (error.code === 'ERR_NETWORK') {
      throw new Error('Cannot connect to server. Please check if the backend is running on port 8080.');
    }
    throw new Error('Failed to fetch budgets');
  }
};

export const createBudget = async (budgetData) => {
  try {
    console.log('Creating budget:', budgetData);
    const response = await budgetApi.post('', budgetData);
    console.log('Budget created:', response.data);
    return response.data;
  } catch (error) {
    console.error('Error creating budget:', error);
    if (error.response?.status === 409) {
      throw new Error('Budget for this category and month already exists');
    }
    if (error.code === 'ERR_NETWORK') {
      throw new Error('Cannot connect to server. Please check if the backend is running.');
    }
    throw new Error('Failed to create budget');
  }
};

export const updateBudget = async (budgetData) => {
  try {
    console.log('Updating budget:', budgetData);
    const response = await budgetApi.put('', budgetData);
    console.log('Budget updated:', response.data);
    return response.data;
  } catch (error) {
    console.error('Error updating budget:', error);
    if (error.response?.status === 404) {
      throw new Error('Budget not found');
    }
    if (error.code === 'ERR_NETWORK') {
      throw new Error('Cannot connect to server. Please check if the backend is running.');
    }
    throw new Error('Failed to update budget');
  }
};

export const deleteBudget = async (id) => {
  try {
    console.log('Deleting budget with ID:', id);
    await budgetApi.delete('', { params: { id } });
    console.log('Budget deleted successfully');
    return true;
  } catch (error) {
    console.error('Error deleting budget:', error);
    if (error.response?.status === 404) {
      throw new Error('Budget not found');
    }
    if (error.code === 'ERR_NETWORK') {
      throw new Error('Cannot connect to server. Please check if the backend is running.');
    }
    throw new Error('Failed to delete budget');
  }
};