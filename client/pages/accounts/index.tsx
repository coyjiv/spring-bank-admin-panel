import Layout from '@/components/layout'
import { initialState, reducer } from '@/state/accounts/accounts';
import { setAccounts } from '@/state/accounts/actions';
import React, { use, useEffect, useReducer, useState } from 'react'
import { Account, SERVER_URL } from '../users';

type Props = {}

const Accounts = (props: Props) => {
  const [state, dispatch] = useReducer(reducer, initialState);
  const [updateCount, setUpdateCount] = useState(0)

  const { accounts, editModalOpen, addModalOpen, removeModalOpen, error, selectedAccount } = state;

  const updateAccount = (accountNew: Account) => {
    const newAccounts = accounts.map((account: Account) => {
      if (account.id === accountNew.id) {
        return {
          ...account,
          number: accountNew.number,
          currency: accountNew.currency,
          balance: accountNew.balance
        }
      }
      return account
    })
    setAccounts(dispatch, newAccounts)
    setUpdateCount(updateCount + 1)
  }

  const createAccount = (account: Account) => {
    setAccounts(dispatch, [ ...accounts, account ])
    setUpdateCount(updateCount + 1)
  }

  const deleteAccount = (account: Account) => {
    const newAccounts = accounts.filter((c: Account) => c.id !== account.id)
    setAccounts(dispatch, newAccounts)
    setUpdateCount(updateCount + 1)
  }

  useEffect(() => {
    const fetchAccounts = async () => {
      const res = await fetch(`${SERVER_URL}/accounts/`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      })
      const data = await res.json()
      console.log(data);
      
      setAccounts(dispatch, data)
    }
    fetchAccounts()
  }, [updateCount])

  return (
    <div>
      <div className="px-4 sm:px-6 lg:px-8">
      <div className="sm:flex sm:items-center">
        <div className="sm:flex-auto">
          <h1 className="text-base font-semibold leading-6 text-gray-900">Accounts</h1>
          <p className="mt-2 text-sm text-gray-700">
            A table of placeholder stock market data that does not make any sense.
          </p>
        </div>
      </div>
      <div className="mt-8 flow-root">
        <div className="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
          <div className="inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8">
            <table className="min-w-full divide-y divide-gray-300">
              <thead>
                <tr>
                  <th
                    scope="col"
                    className="whitespace-nowrap py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-0"
                  >
                    Account ID
                  </th>
                  <th
                    scope="col"
                    className="whitespace-nowrap px-2 py-3.5 text-left text-sm font-semibold text-gray-900"
                  >
                    Currency
                  </th>
                  <th
                    scope="col"
                    className="whitespace-nowrap px-2 py-3.5 text-left text-sm font-semibold text-gray-900"
                  >
                    Balance
                  </th>
                  <th scope="col" className="relative whitespace-nowrap py-3.5 pl-3 pr-4 sm:pr-0">
                    <span className="sr-only">Edit</span>
                  </th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-200 bg-white">
                {accounts.map((account:Account) => (
                  <tr key={account.id}>
                    <td className="whitespace-nowrap py-2 pl-4 pr-3 text-sm text-gray-500 sm:pl-0">{account.number}</td>
                    <td className="whitespace-nowrap px-2 py-2 text-sm text-gray-500">{account.currency}</td>
                    <td className="whitespace-nowrap px-2 py-2 text-sm font-medium text-gray-900">
                      {account.balance}<BalanceIcon account={account}/>
                    </td>
                    <td className="relative whitespace-nowrap py-2 pl-3 pr-4 text-right text-sm font-medium sm:pr-0">
                      <a href="#" className="text-indigo-600 hover:text-indigo-900">
                        Edit<span className="sr-only">, {account.id}</span>
                      </a>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    </div>
  )
}

Accounts.getLayout = function getLayout(page: React.ReactElement) {
    return <Layout>{page}</Layout>
}

export default Accounts


const BalanceIcon = ({account}:{account:Account}) => {
  switch (account.currency) {
    case 'EUR':
      return <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6 inline scale-75">
      <path strokeLinecap="round" strokeLinejoin="round" d="M14.25 7.756a4.5 4.5 0 100 8.488M7.5 10.5h5.25m-5.25 3h5.25M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
    </svg>    
    case 'USD':
      return <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6 inline scale-75">
      <path strokeLinecap="round" strokeLinejoin="round" d="M12 6v12m-3-2.818l.879.659c1.171.879 3.07.879 4.242 0 1.172-.879 1.172-2.303 0-3.182C13.536 12.219 12.768 12 12 12c-.725 0-1.45-.22-2.003-.659-1.106-.879-1.106-2.303 0-3.182s2.9-.879 4.006 0l.415.33M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
    </svg>    
    case 'GBP':
      return <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6 inline scale-75">
      <path strokeLinecap="round" strokeLinejoin="round" d="M14.121 7.629A3 3 0 009.017 9.43c-.023.212-.002.425.028.636l.506 3.541a4.5 4.5 0 01-.43 2.65L9 16.5l1.539-.513a2.25 2.25 0 011.422 0l.655.218a2.25 2.25 0 001.718-.122L15 15.75M8.25 12H12m9 0a9 9 0 11-18 0 9 9 0 0118 0z" />
    </svg>    
    default:
      return <span className="text-gray-500 group-hover:text-gray-700">{account.balance}</span>
  }
}