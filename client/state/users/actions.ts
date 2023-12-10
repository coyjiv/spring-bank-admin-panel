import { Customer } from "@/pages/users";
import { UsersAction } from "./users";

const handleEditClick = (dispatch:React.Dispatch<UsersAction>, customer: Customer) => {
    dispatch({ type: 'SET_SELECTED_CUSTOMER', payload: customer });
    dispatch({ type: 'TOGGLE_EDIT_MODAL', payload: true });
  };
  
const handleEditModalClose = (dispatch:React.Dispatch<UsersAction>) => {
    dispatch({ type: 'TOGGLE_EDIT_MODAL', payload: false });
  };
  
const setAddModal = (dispatch:React.Dispatch<UsersAction>, currentValue: boolean) => {dispatch({ type: 'TOGGLE_ADD_MODAL', payload: currentValue })};

const setRemoveModal = (dispatch:React.Dispatch<UsersAction>, currentValue: boolean) => dispatch({ type: 'TOGGLE_REMOVE_MODAL', payload: currentValue });

const updateCustomer = (dispatch:React.Dispatch<UsersAction>, customerNew: Customer) => {
  dispatch({ type: 'UPDATE_CUSTOMER', payload: customerNew });
};

const createCustomer = (dispatch:React.Dispatch<UsersAction>, customer: Customer) => {
  dispatch({ type: 'CREATE_CUSTOMER', payload: customer });
};

const deleteCustomer = (dispatch:React.Dispatch<UsersAction>, customer: Customer) => {
  dispatch({ type: 'DELETE_CUSTOMER', payload: customer });
  setRemoveModal(dispatch, false);
};

const setCustomers = (dispatch:React.Dispatch<UsersAction>, customers: Customer[] | []) => {
    dispatch({ type: 'SET_CUSTOMERS', payload: customers });
    };  

export { handleEditClick, handleEditModalClose, updateCustomer, createCustomer, deleteCustomer, setCustomers, setAddModal, setRemoveModal };
  