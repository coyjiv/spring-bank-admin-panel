import React from 'react'
import { Dashboard } from './Dashboard'

type Props = {
    children: React.ReactNode
}

const Layout = (props: Props) => {
  return (
   <Dashboard component={props.children}/>
  )
}

export default Layout