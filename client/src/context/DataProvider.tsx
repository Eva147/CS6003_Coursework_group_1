import React, { createContext, useEffect, useState, ReactNode } from 'react';
import axios from 'axios';
import { Product, AuthResponse, User } from '../dataTypes';

import { Catalog } from '../dataTypes';

export type DataContextType = {
  data: Catalog[] | null;
  products: Product[];
  sortedProducts: Product[];
  isLoading: boolean;
  error: string | null;
  user: User | null;
  isAuthenticated: boolean;
  fetchProductsByCatalogById: (catalogId: string) => void;
  getProductById: (productId: string) => Product | undefined;
  getCatalogTitleById: (catalogId: string) => string | undefined;
  getSortedProducts: (catalogId: string, sortParameter: string) => void;
  loginUser: (userData: User) => Promise<AuthResponse>;
  logoutUser: () => void;
};

export const DataContext = createContext<DataContextType>({
  data: [],
  products: [],
  sortedProducts: [],
  isLoading: true,
  error: null,
  user: null,
  isAuthenticated: false,
  fetchProductsByCatalogById: () => {},
  getProductById: (productId: string) => undefined,
  getCatalogTitleById: (catalogId: string) => undefined,
  getSortedProducts: (catalogId: string, sortParameter: string) => {},
  loginUser: (userData: User) => Promise.resolve({
    success: false,
    message: 'Login not implemented'
  }),
  logoutUser: () => {},
});

interface DataProviderProps {
  children: ReactNode;
}

export const DataProvider: React.FC<DataProviderProps> = ({ children }) => {
  const [data, setData] = useState<Catalog[] | null>(null);
  const [products, setProducts] = useState<Product[]>([]);
  const [currentProduct, setCurrentProduct] = useState<Product | null>(null);
  const [sortedProducts, setSortedProducts] = useState(products);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [user, setUser] = useState<User | null>(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    setIsLoading(true);
    axios
      .get("http://localhost:8080")
      .then(response => {
        setData(response.data);
        setIsLoading(false);
      })
      .catch(error => {
        setError("Error fetching data");
        setIsLoading(false);
      });
  }, []);

  const fetchProductsByCatalogById = (catalogId: string) => {
    setIsLoading(true);
    axios
      .get(`http://localhost:8080/${catalogId}`)
      .then(response => {
        setProducts(response.data);
        setIsLoading(false);
      })
      .catch(error => {
        console.error(`Error fetching data for catalog ${catalogId}:`, error);
        setError("Error fetching catalog data");
        setIsLoading(false);
      });
  };

  const getProductById = (productId: string): Product | undefined => {
    const existingProduct = products.find(p => p.productId === productId);
    setCurrentProduct(existingProduct || null);
    return existingProduct;
};

  const getCatalogTitleById = (catalogId: string) => {
    return data?.find(catalog => catalog.id === catalogId)?.category;
  };

  const getSortedProducts = (catalogId: string, sortParameter: string) => {
    setIsLoading(true);
    axios
      .get(`http://localhost:8080/${catalogId}/${sortParameter}`)
      .then(response => {
        setSortedProducts(response.data);
        setIsLoading(false);
      })
      .catch(error => {
        console.error(`Error fetching data for catalog ${catalogId}:`, error);
        setError("Error fetching catalog data");
        setIsLoading(false);
      });
  }

  const loginUser = async (userData: User): Promise<AuthResponse> => {
    setIsLoading(true);
    try {
        const response = await axios.post('http://localhost:8080/login', userData);
        const userRecord = {
          email: response.data.split("'")[1],
          name: response.data.split("'")[5]
      };

        if (response.data) {
            setUser(userRecord);
            setIsAuthenticated(true);
            return {
                success: true,
                message: 'Login successful',
            };
        } else {
            console.log('Login failed:', response.data.message);
            return {
                success: false,
                message: response.data.message || 'Login failed'
            };
        }
    } catch (err) {
        setError('Login failed');
        return {
            success: false,
            message: 'Login failed'
        };
    } finally {
        setIsLoading(false);
    }
  };

  const logoutUser = () => {
    setUser(null);
    setIsAuthenticated(false);
  };

  return (
    <DataContext.Provider value={{
      data,
      products,
      sortedProducts,
      isLoading,
      error,
      fetchProductsByCatalogById,
      getProductById,
      getCatalogTitleById,
      getSortedProducts,
      user,
      isAuthenticated,
      loginUser,
      logoutUser
      }}>
      {children}
    </DataContext.Provider>
  );
};
