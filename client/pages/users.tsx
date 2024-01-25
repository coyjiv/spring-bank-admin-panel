'use client'
import AddUserModal from '@/components/AddUserModal';
import EditCustomerModal from '@/components/EditCustomerModal';
import Layout from '@/components/layout'
import RemoveUserModal from '@/components/RemoveUserModal';
import { UsersList } from '@/components/UserList';
import { handleEditClick, handleEditModalClose, setAddModal, setCustomers, setRemoveModal } from '@/state/users/actions';
import { initialState, reducer, UsersState } from '@/state/users/users';
import { useRouter } from 'next/router';
import { createContext, useEffect, useReducer, useState } from 'react';

export interface Customer {
  name: string;
  email: string;
  image: string;
  age: number;
  id: number;
  password?: string;
  phone?: string;
  accounts: Account[] | []
  // role: string;
}

export interface Account {
  id: number;
  number: string,
  currency: "EUR" | "USD" | "GBP" | "CHF" | "UAH"
  balance: number;
}

export const SERVER_URL = "http://localhost:9000"

export const PaginationContext = createContext<UsersState>(initialState)

const Users = () => {

  const router = useRouter()

  const [ state, dispatch ] = useReducer(reducer, initialState);
  const [ updateCount, setUpdateCount ] = useState(0);
  const [ isLoading, setIsLoading ] = useState(false);


  const { customers, editModalOpen, addModalOpen, removeModalOpen, error, selectedCustomer, pagination } = state;

  const updateCustomer = (customerNew: Customer) => {
    const newCustomers = customers.map((customer: Customer) => {
      if (customer.id === customerNew.id) {
        return {
          ...customer,
          name: customerNew.name,
          email: customerNew.email,
          age: customerNew.age
        }
      }
      return customer
    })
    setCustomers(dispatch, newCustomers)
    setUpdateCount(updateCount + 1)
  }

  const createCustomer = (customer: Customer) => {
    setCustomers(dispatch, [ ...customers, customer ])
    setUpdateCount(updateCount + 1)
  }

  const deleteCustomer = (customer: Customer) => {
    const newCustomers = customers.filter((c: Customer) => c.id !== customer.id)
    setCustomers(dispatch, newCustomers)
    setUpdateCount(updateCount + 1)
  }



  useEffect(() => {
    if (!router.query.page) {
      router.query.page = '0'
      router.push(router);
    }
    if (!router.query.limit) {
      router.query.limit = '10'
      router.push(router);
    }
  }, [])

  useEffect(() => {
    if (!isLoading) {
      setIsLoading(true)
    }
    fetch(SERVER_URL + '/customers/' + router.query.page + '/' + router.query.limit, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then(response => {
        response.json().then(data => {
          setCustomers(dispatch, data)
          setIsLoading(false)
        })
          .catch(err => {
            console.log(err);
            setIsLoading(false)
          })
      })
  }, [ updateCount, router.query.page, router.query.limit ])



  return (
    <PaginationContext.Provider value={state}>
      <div className="px-4 sm:px-6 lg:px-8">
        <div className="sm:flex sm:items-center">
          <div className="sm:flex-auto">
            <h1 className="text-base font-semibold leading-6 text-gray-900">Users</h1>
            <p className="mt-2 text-sm text-gray-700">
              A list of all the users in your account including their name, email and status.
            </p>
          </div>
          <div className="mt-4 flex gap-2 sm:ml-16 sm:mt-0 sm:flex-none">
            <button
              onClick={() => setAddModal(dispatch, true)}
              type="button"
              className="block rounded-md bg-indigo-600 px-3 py-2 text-center text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            >
              Add user
            </button>
            <button
              onClick={() => setRemoveModal(dispatch, true)}
              type="button"
              className="block rounded-md bg-red-600 px-3 py-2 text-center text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-red-600"
            >
              Remove user
            </button>
          </div>
        </div>
        <div className="mt-8 flow-root">
          <div className="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
            <div className="inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8">
              <ul className='grid grid-cols-3'>
                <li className="py-3.5 pl-4 pr-3 ml-[80px] text-left text-sm font-semibold text-gray-900 sm:pl-0">
                  Name
                </li>
                {/* <th scope="col" className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">
                      Title
                    </th> */}
                <li className="px-3 py-3.5  text-left text-sm font-semibold text-gray-900">
                  Status
                </li>
                {/* <th scope="col" className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">
                      Role
                    </th> */}
                <li className="relative py-3.5 pl-3 pr-4 sm:pr-0">
                  <span className="sr-only">Edit</span>
                </li>
              </ul>
              <div className='grid grid-cols-1 w-full gap-5'>
                {error && <div className='text-red-500'>Error: {error}</div>}
                {isLoading ? <div className='text-gray-500'>Loading...</div>
                  :
                  <UsersList updateCount={updateCount} setUpdateCount={setUpdateCount} dispatch={dispatch} customers={customers} handleEditClick={
                    (customer: Customer) => handleEditClick(dispatch, customer)
                  } />}
              </div>
            </div>
          </div>
        </div>
        {!!selectedCustomer && <EditCustomerModal handleUpdate={updateCustomer} customer={selectedCustomer} handleClose={
          () => handleEditModalClose(dispatch)
        } open={editModalOpen} />}
        <AddUserModal open={addModalOpen} handleClose={
          () => setAddModal(dispatch, false)
        } handleUpdate={createCustomer} />
        {customers.length > 0 && <RemoveUserModal customers={customers} open={removeModalOpen} handleClose={() => setRemoveModal(dispatch, false)} handleUpdate={deleteCustomer} />}

      </div>
    </PaginationContext.Provider>
  )
}

Users.getLayout = function getLayout(page: React.ReactElement) {
  return <Layout>{page}</Layout>
}

export default Users


