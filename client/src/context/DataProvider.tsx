import React, { createContext, useEffect, useState, ReactNode } from 'react';
import axios from 'axios';

export type DataContextType = {
  data: string | null;
  isLoading: boolean;
  error: string | null;
};

export const DataContext = createContext<DataContextType>({
  data: null,
  isLoading: true,
  error: null,
});

interface DataProviderProps {
  children: ReactNode;
}

export const DataProvider: React.FC<DataProviderProps> = ({ children }) => {
  const [data, setData] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080")
      .then(response => {
        console.log(response.data)
        setData(response.data);
        setIsLoading(false);
      })
      .catch(error => {
        setError("Error fetching data");
        setIsLoading(false);
      });
  }, []);

  return (
    <DataContext.Provider value={{ data, isLoading, error }}>
      {children}
    </DataContext.Provider>
  );
};
