
INSERT INTO transaction_db.account (id, account_type) VALUES
  ('ACC-001', 'STANDARD'),
  ('ACC-002', 'PREMIUM'),
  ('ACC-003', 'GOLD'),
  ('ACC-004', 'STANDARD')
    ON CONFLICT (id) DO NOTHING;

INSERT INTO transaction_db.transaction (id, account_id, created_at, transaction_type, amount, reason) VALUES
  ('TRX-001', 'ACC-001', '2026-08-01T08:30:00Z', 'IN', 1500.00, 'Initial deposit'),
  ('TRX-002', 'ACC-001', '2026-08-02T10:15:00Z', 'OUT', 250.00, 'Online purchase'),
  ('TRX-003', 'ACC-001', '2026-08-03T14:20:00Z', 'IN', 500.00, 'Salary adjustment'),
  ('TRX-004', 'ACC-002', '2026-08-01T09:00:00Z', 'IN', 5000.00, 'Initial deposit'),
  ('TRX-005', 'ACC-002', '2026-08-04T16:45:00Z', 'OUT', 850.00, 'Rent payment'),
  ('TRX-006', 'ACC-003', '2026-08-05T11:00:00Z', 'IN', 12000.00, 'Investment deposit'),
  ('TRX-007', 'ACC-003', '2026-08-06T13:30:00Z', 'OUT', 1200.00, 'Travel booking'),
  ('TRX-008', 'ACC-004', '2026-08-07T07:50:00Z', 'IN', 3000.00, 'Freelance payment'),
  ('TRX-009', 'ACC-004', '2026-08-08T18:10:00Z', 'OUT', 400.00, 'Utilities'),
  ('TRX-010', 'ACC-004', '2026-08-09T12:00:00Z', 'IN', 750.00, 'Refund')
    ON CONFLICT (id) DO NOTHING;