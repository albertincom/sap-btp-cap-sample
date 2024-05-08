namespace my.bookshop;

using { Currency, managed, sap } from '@sap/cds/common';


@cds.persistence.journal
entity Books {
  key ID : Integer;
  title  : String;
  stock  : Integer;
}
