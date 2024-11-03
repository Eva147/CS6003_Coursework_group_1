import { useContext } from "react";
import { DataContext, DataContextType } from "../context/DataProvider";

export const useData = (): DataContextType => {
  const context = useContext(DataContext);
  if (context === undefined) {
    throw new Error("no data context provided");
  }
  return context;
};