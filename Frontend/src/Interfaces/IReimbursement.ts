
export interface IReimbursement {
    reimburseId?: number,
    amount: number,
    submittedDate?: string,
    resolvedDate?: string,
    description: string,
    reimburseAuthor?: string,
    reimburseResolver?: string, 
    reimburseStatus?: string,
    reimburseType: string,
}