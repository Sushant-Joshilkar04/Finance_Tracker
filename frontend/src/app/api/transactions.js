import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/transactions';

const transactionApi = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getTransactions = async () => {
  try {
    const response = await transactionApi.get('');
    return response.data;
  } catch (error) {
    console.error('Error fetching transactions:', error);
    throw new Error('Failed to fetch transactions');
  }
};


export const createTransaction = async (transactionData) => {
  try {
    const formattedData = {
      ...transactionData,
      date: new Date(transactionData.date).toISOString(),
    };
    
    const response = await transactionApi.post('', formattedData);
    return response.data;
  } catch (error) {
    console.error('Error creating transaction:', error);
    if (error.response?.status === 400) {
      throw new Error('Invalid transaction data');
    }
    throw new Error('Failed to create transaction');
  }
};

export const updateTransaction = async (id, transactionData) => {
  try {
    const formattedData = {
      ...transactionData,
      date: new Date(transactionData.date).toISOString(),
    };
    
    const response = await transactionApi.put(`/${id}`, formattedData);
    return response.data;
  } catch (error) {
    console.error('Error updating transaction:', error);
    if (error.response?.status === 404) {
      throw new Error('Transaction not found');
    }
    throw new Error('Failed to update transaction');
  }
};

export const deleteTransaction = async (id) => {
  try {
    await transactionApi.delete(`/${id}`);
    return true;
  } catch (error) {
    console.error('Error deleting transaction:', error);
    if (error.response?.status === 404) {
      throw new Error('Transaction not found');
    }
    throw new Error('Failed to delete transaction');
  }
};