import { Customer } from "@/pages/users";
import React from "react";
import { Disclose } from "./Disclose";
import Pagination from "./Pagination";
import { UserAccounts } from "./UserAccounts";

interface IUserList {
  customers: Customer[];
  handleEditClick: (arg: Customer) => void;
  dispatch: React.Dispatch<any>;
  updateCount: number;
  setUpdateCount: React.Dispatch<React.SetStateAction<number>>;
}

export const UsersList = ({ customers, handleEditClick }: IUserList) => (
  <>
    {customers.length > 0 ? customers.map((customer: Customer) => (
      <Disclose className="border rounded-2xl px-5" key={customer.id} buttonContent={
        <div className='grid grid-cols-3 items-center px-3'>
          <div className="whitespace-nowrap py-5 pl-4 pr-3 text-sm sm:pl-0">
            <div className="flex items-center">
              <div className="h-11 w-11 flex items-center flex-shrink-0">
                {customer?.image ?
                  <img className="h-11 w-11 rounded-full" src={customer?.image} alt="" />
                  : <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-7 h-7">
                    <path strokeLinecap="round" strokeLinejoin="round" d="M17.982 18.725A7.488 7.488 0 0012 15.75a7.488 7.488 0 00-5.982 2.975m11.963 0a9 9 0 10-11.963 0m11.963 0A8.966 8.966 0 0112 21a8.966 8.966 0 01-5.982-2.275M15 9.75a3 3 0 11-6 0 3 3 0 016 0z" />
                  </svg>
                }
              </div>
              <div className="ml-4">
                <div className="font-medium text-gray-900">{customer.name}</div>
                <div className="mt-1 text-gray-500">{customer.email}</div>
              </div>
            </div>
          </div>
          <div className="whitespace-nowrap px-3 py-5 text-sm text-gray-500 flex items-center">
            <span className="inline-flex items-center rounded-md bg-green-50 px-2 py-1 text-xs font-medium text-green-700 ring-1 ring-inset ring-green-600/20">
              Active
            </span>
          </div>
          <div className="relative whitespace-nowrap py-5 pl-3 pr-4 text-right text-sm font-medium sm:pr-0 ml-auto">
            <button onClick={() => handleEditClick(customer)} className="text-indigo-600 hover:text-indigo-900">
              Edit<span className="sr-only">, {customer.name}</span>
            </button>
          </div>
        </div>

      }>
        <UserAccounts accounts={customer.accounts} />
      </Disclose>

    ))
      : <div className='text-gray-500'>No users</div>}
    <Pagination />
  </>
)