import { Customer } from "@/pages/users";

export const initialState = {
    customers: [],
    editModalOpen: false,
    addModalOpen: false,
    removeModalOpen: false,
    error: null,
    selectedCustomer: null,
    pagination: {
      page: 1,
      limit: 10,
      total: 10
    },
  };
 export interface UsersState {
    customers: Customer[] | [];
    editModalOpen: boolean;
    addModalOpen: boolean;
    removeModalOpen: boolean;
    error: null;
    selectedCustomer: Customer | null;
    pagination: {
      page: number;
      limit: number;
      total: number;
    }
  }
  export interface UsersAction {
    type:   'SET_CUSTOMERS' | 
            'TOGGLE_EDIT_MODAL' |
            'TOGGLE_ADD_MODAL' |
            'TOGGLE_REMOVE_MODAL' |
            'SET_SELECTED_CUSTOMER' |
            'UPDATE_CUSTOMER' |
            'CREATE_CUSTOMER' |
            'DELETE_CUSTOMER' | 
            'SET_PAGINATION' |
            'NEXT_PAGE' |
            'PREV_PAGE';
    payload: any;
  }
  
export  function reducer(state:UsersState, action: 
        UsersAction
    ) {
    switch (action.type) {
      case 'SET_CUSTOMERS':
        return { ...state, customers: action.payload };
      case 'TOGGLE_EDIT_MODAL':
        return { ...state, editModalOpen: action.payload };
      case 'TOGGLE_ADD_MODAL':
        return { ...state, addModalOpen: action.payload };
      case 'TOGGLE_REMOVE_MODAL':
        return { ...state, removeModalOpen: action.payload };
      case 'SET_SELECTED_CUSTOMER':
        return { ...state, selectedCustomer: action.payload };
      case 'UPDATE_CUSTOMER':
        const updatedCustomers = state.customers.map(customer => {
          if (customer.id === action.payload.id) {
            return action.payload;
          }
          return customer;
        });
        return { ...state, customers: updatedCustomers };
      case 'CREATE_CUSTOMER':
        return { ...state, customers: [...state.customers, action.payload] };
      case 'DELETE_CUSTOMER':
        const filteredCustomers = state.customers.filter(customer => customer.id !== action.payload.id);
        return { ...state, customers: filteredCustomers };
        case 'SET_PAGINATION':
          return { ...state, pagination: action.payload };
        case 'NEXT_PAGE':
          if(state.pagination.page === state.pagination.total) {
            return state;
          }
          return { ...state, pagination: { ...state.pagination, page: state.pagination.page + 1 } };
          case 'PREV_PAGE':
            if(state.pagination.page === 1) {
              return state;
            }
            return { ...state, pagination: { ...state.pagination, page: state.pagination.page - 1 } };
      default:
        return state;
    }
  }
  