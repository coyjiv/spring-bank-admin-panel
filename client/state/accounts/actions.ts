import { Account } from "@/pages/users";
import { AccountAction } from "./accounts";

const handleEditClick = (dispatch:React.Dispatch<AccountAction>, account: Account) => {
    dispatch({ type: 'SET_SELECTED_ACCOUNT', payload: account });
    dispatch({ type: 'TOGGLE_EDIT_MODAL', payload: true });
  };
  
const handleEditModalClose = (dispatch:React.Dispatch<AccountAction>) => {
    dispatch({ type: 'TOGGLE_EDIT_MODAL', payload: false });
  };
  
const setAddModal = (dispatch:React.Dispatch<AccountAction>, currentValue: boolean) => {dispatch({ type: 'TOGGLE_ADD_MODAL', payload: currentValue })};

const setRemoveModal = (dispatch:React.Dispatch<AccountAction>, currentValue: boolean) => dispatch({ type: 'TOGGLE_REMOVE_MODAL', payload: currentValue });

const updateAccount = (dispatch:React.Dispatch<AccountAction>, accountNew: Account) => {
  dispatch({ type: 'UPDATE_ACCOUNT', payload: accountNew });
};

const createAccount = (dispatch:React.Dispatch<AccountAction>, account: Account) => {
  dispatch({ type: 'CREATE_ACCOUNT', payload: account });
};

const deleteAccount = (dispatch:React.Dispatch<AccountAction>, account: Account) => {
  dispatch({ type: 'DELETE_ACCOUNT', payload: account });
  setRemoveModal(dispatch, false);
};

const setAccounts = (dispatch:React.Dispatch<AccountAction>, accounts: Account[] | []) => {
    dispatch({ type: 'SET_ACCOUNTS', payload: accounts });
    };  

export { handleEditClick, handleEditModalClose, updateAccount, createAccount, deleteAccount, setAccounts, setAddModal, setRemoveModal };