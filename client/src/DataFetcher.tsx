import React, { useEffect, useState } from 'react';
import axios from 'axios';

const DataFetcher: React.FC = () => {
    const [data, setData] = useState<string | null>(null);

    useEffect(() => {
        axios.get("http://localhost:8080")
            .then(response => setData(response.data))
            .catch(error => console.error("Error fetching data:", error));
    }, []);

    return (
        <div>{data || "Loading..."}</div>
    );
};

export default DataFetcher;