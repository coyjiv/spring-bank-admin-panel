import { Account } from "@/pages/users";

export const initialState = {
    accounts: [],
    editModalOpen: false,
    addModalOpen: false,
    removeModalOpen: false,
    error: null,
    selectedAccount: null
  };
 export interface AccountState {
    accounts: Account[] | [];
    editModalOpen: boolean;
    addModalOpen: boolean;
    removeModalOpen: boolean;
    error: null;
    selectedAccount: Account | null;
  }
  export interface AccountAction {
    type:   'SET_ACCOUNTS' | 
            'TOGGLE_EDIT_MODAL' |
            'TOGGLE_ADD_MODAL' |
            'TOGGLE_REMOVE_MODAL' |
            'SET_SELECTED_ACCOUNT' |
            'UPDATE_ACCOUNT' |
            'CREATE_ACCOUNT' |
            'DELETE_ACCOUNT';
    payload: any;
  }
  
export  function reducer(state:AccountState, action: 
        AccountAction
    ) {
    switch (action.type) {
      case 'SET_ACCOUNTS':
        return { ...state, accounts: action.payload };
      case 'TOGGLE_EDIT_MODAL':
        return { ...state, editModalOpen: action.payload };
      case 'TOGGLE_ADD_MODAL':
        return { ...state, addModalOpen: action.payload };
      case 'TOGGLE_REMOVE_MODAL':
        return { ...state, removeModalOpen: action.payload };
      case 'SET_SELECTED_ACCOUNT':
        return { ...state, selectedCustomer: action.payload };
      case 'UPDATE_ACCOUNT':
        const updatedCustomers = state.accounts.map(account => {
          if (account.id === action.payload.id) {
            return action.payload;
          }
          return account;
        });
        return { ...state, accounts: updatedCustomers };
      case 'CREATE_ACCOUNT':
        return { ...state, accounts: [...state.accounts, action.payload] };
      case 'DELETE_ACCOUNT':
        const filteredCustomers = state.accounts.filter(account => account.id !== action.payload.id);
        return { ...state, accounts: filteredCustomers };
      default:
        return state;
    }
  }