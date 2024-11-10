import React, { createContext, useEffect, useState, ReactNode } from 'react';
import axios from 'axios';
import { Product } from '../dataTypes';

import { Catalog } from '../dataTypes';

export type DataContextType = {
  data: Catalog[] | null;
  products: Product[];
  isLoading: boolean;
  error: string | null;
  fetchProductsByCatalogById: (catalogId: string) => void;
};

export const DataContext = createContext<DataContextType>({
  data: [],
  products: [],
  isLoading: true,
  error: null,
  fetchProductsByCatalogById: () => {},
});

interface DataProviderProps {
  children: ReactNode;
}

export const DataProvider: React.FC<DataProviderProps> = ({ children }) => {
  const [data, setData] = useState<Catalog[] | null>(null);
  const [products, setProducts] = useState<Product[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

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

  return (
    <DataContext.Provider value={{ data, products, isLoading, error, fetchProductsByCatalogById }}>
      {children}
    </DataContext.Provider>
  );
};
