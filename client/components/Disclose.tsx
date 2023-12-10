import React, { useState } from 'react';
import AnimateHeight from 'react-animate-height';

type Props = {
buttonContent:JSX.Element;
children: JSX.Element | Array<JSX.Element>;
initialOpen?: boolean;
className?: string;
};
export function Disclose({ buttonContent, children, initialOpen = false, className='' }: Props) {
const [open, setOpen] = useState(initialOpen);

return (

<div className={className}>
    <div className='accordion_button w-full cursor-pointer' onClick={(e) =>{
        if((e.target as HTMLElement).tagName !== 'BUTTON'){
            setOpen(!open)
        }
    } }>
        {buttonContent}
    </div>
    
    <AnimateHeight id={'sliding_wrapper'} duration={200} height={open ? 'auto' : 0}>
    {children}
    </AnimateHeight>
</div>


);
}